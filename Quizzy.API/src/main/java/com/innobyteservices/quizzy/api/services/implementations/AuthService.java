package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.LoginRequest;
import com.innobyteservices.quizzy.api.dto.request.SignUpRequest;
import com.innobyteservices.quizzy.api.dto.response.LoginResponse;
import com.innobyteservices.quizzy.api.dto.response.SignUpResponse;
import com.innobyteservices.quizzy.api.dto.response.UserResponse;
import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.exceptions.*;
import com.innobyteservices.quizzy.api.helpers.PasswordHelper;
import com.innobyteservices.quizzy.api.internals.Token;
import com.innobyteservices.quizzy.api.internals.TokenPayload;
import com.innobyteservices.quizzy.api.internals.UserFilter;
import com.innobyteservices.quizzy.api.entities.User;
import com.innobyteservices.quizzy.api.repositories.interfaces.IUserRepository;
import com.innobyteservices.quizzy.api.services.interfaces.IAuthService;
import com.innobyteservices.quizzy.api.services.interfaces.ITokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository _repository;
    private final ITokenService _tokenService;
    private final ModelMapper _mapper;

    @Autowired
    private PasswordHelper _passwordHelper;

    /**
     * Constructs an {@code AuthService} with the required dependencies.
     *
     * @param repository   the user repository used to interact with the user data source
     * @param tokenService the token service used to generate authentication tokens
     * @param mapper       the model mapper used to map between DTOs and entities
     */
    public AuthService(IUserRepository repository, ITokenService tokenService, ModelMapper mapper) {
        _repository = repository;
        _tokenService = tokenService;
        _mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SignUpResponse signup(SignUpRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        // Check if the user exists with the same email or not
        UserFilter filter = _mapper.map(request, UserFilter.class);
        Optional<User> user = _repository.get(filter);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Create a new user
        var newUser = _mapper.map(request, User.class);
        newUser.setRole(AccessRole.User);
        Optional<Integer> userId = _repository.create(newUser);

        // Check whether user is created or not
        if (userId.isPresent() && userId.get() != -1) {
            SignUpResponse response = new SignUpResponse();
            response.setId(userId.get());
            return response;
        }
        else {
            throw new SignUpFailedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        // Fetch user
        UserFilter filter = _mapper.map(request, UserFilter.class);
        Optional<User> user = _repository.get(filter);
        if (user.isPresent()) {
            // Compare password hash
            if (!_passwordHelper.verify(request.getPassword(), user.get().getPassword())) {
                throw new LoginFailedException();
            }
            else {
                // Generate access token
                TokenPayload payload = new TokenPayload();
                payload.setUserId(user.get().getId());
                payload.setEmail(user.get().getEmail());
                payload.setRole(user.get().getRole());
                Token token = _tokenService.generateToken(payload);

                // Create response object
                LoginResponse response = new LoginResponse();
                response.setAccessToken(token.getValue());
                response.setExpiresAt(token.getExpiresAt());
                response.setUser(_mapper.map(user, UserResponse.class));
                return response;
            }
        }
        else {
            throw new LoginFailedException();
        }
    }
}
