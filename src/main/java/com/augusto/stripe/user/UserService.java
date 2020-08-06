package com.augusto.stripe.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
    }

    public User update(Long id, UserDTO user) {
        return userRepository
                .findById(id)
                .map(u -> {
                    u.setId(id);
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setDescription(user.getDescription());
                    u.setPhone(user.getPhone());
                    u.setStripeId(user.getStripeId());
                    userRepository.save(u);
                    return u;
                }).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
}
