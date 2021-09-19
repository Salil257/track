package com.syzygy.track.controller;

import com.syzygy.track.dto.productdto.HolderDto;
import com.syzygy.track.dto.productdto.ProductDetailDto;
import com.syzygy.track.dto.userdto.UpdateUserDto;
import com.syzygy.track.dto.userdto.UserDto;
import com.syzygy.track.dto.userdto.UserProductDto;
import com.syzygy.track.dto.userdto.UserWrap;
import com.syzygy.track.dto.authenticate.AuthenticateRequest;
import com.syzygy.track.dto.authenticate.AuthenticateResponse;
import com.syzygy.track.entity.Holder;
import com.syzygy.track.entity.Product;
import com.syzygy.track.mapper.DtoMapper;
import com.syzygy.track.repository.UserDataRepository;
import com.syzygy.track.repository.UserProductRepository;
import com.syzygy.track.repository.UserRepository;
import com.syzygy.track.service.ProductService;
import com.syzygy.track.service.UserService;
import com.syzygy.track.model.User;
import com.syzygy.track.model.UserData;
import com.syzygy.track.model.UserProduct;
import com.syzygy.track.utils.JWTUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JWTUtilty jwtUtility;
    @Autowired
    private UserDetailsService userService;
    @Autowired
    private UserService userServicerepo;

    private HttpServletResponse response;
    private HttpServletRequest request;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DtoMapper dtoMapper;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private ProductService productService;



    @PostMapping("/authenticate")
    public AuthenticateResponse authenticate(@Valid @RequestBody AuthenticateRequest jwtRequest) throws RuntimeException{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        return  new AuthenticateResponse(token);
    }

    @PostMapping("/register")
    public String userRegister(@Valid @RequestBody User user) {
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        userRepository.save(user1);
        return "save";
    }

    @GetMapping("/loginUserDetail")
    public UserDto loginhome(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
       String  userName = jwtUtility.getUsernameFromToken(token);
       User user = userRepository.findByUserName(userName).get();
        return dtoMapper.userTouserDto(user);
    }

    @GetMapping("/UserDetail")
    public UserWrap UserDetail(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        UserWrap userWrap = new UserWrap();
        userWrap.setUserDto(dtoMapper.userTouserDto(user));
        userWrap.setUpdateUserDto(dtoMapper.userDataToUpdateuserDto(userDataRepository.findByUser(user)));
        return userWrap;
    }

    @PostMapping("/scan")
    public String scan(HttpServletRequest request,@Valid @RequestBody ProductDetailDto productDetailDto) {
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        return userServicerepo.Scan(productDetailDto,user);
    }

    @GetMapping("/home")
    public String getProduct() {
        return "kehome save";
    }

    @PostMapping("/saveUserDetails")
    public String updateUser(HttpServletRequest request,@Valid @RequestBody UpdateUserDto updateUserDto) {
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        UserData userData = new UserData();
        userData.setUser(user);
        userData.setFirstName(updateUserDto.getFirstName());
        userData.setLastName(updateUserDto.getLastName());
        userData.setEmail(updateUserDto.getEmail());
        userData.setAddress(updateUserDto.getAddress());
        userData.setPincode(updateUserDto.getPincode());
        userDataRepository.save(userData);
        return "updated user data";
    }

    @PostMapping("/updateUserDetails")
    public String updateUserDetail(HttpServletRequest request,@Valid @RequestBody UpdateUserDto updateUserDto) {
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        UserData userData = userDataRepository.findByUser(user);
        userData.setFirstName(updateUserDto.getFirstName());
        userData.setLastName(updateUserDto.getLastName());
        userData.setEmail(updateUserDto.getEmail());
        userData.setAddress(updateUserDto.getAddress());
        userData.setPincode(updateUserDto.getPincode());
        userDataRepository.save(userData);
        return "updated user data";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(HttpServletRequest request ,@Valid @RequestBody Product product) {
        List<Holder> holderList = new ArrayList<Holder>();
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        UserData userData= userDataRepository.findByUser(user);

        /* adding holder to product*/
        HolderDto holderDto = dtoMapper.userToHolderDto(user);
        Holder holder = new Holder();
        holder.setUserName(holderDto.getUserName());
        holder.setRole(holderDto.getRole());
        holder.setPhoneNo(holderDto.getPhoneNo());
        holder.setLocation(userData.getAddress());
        holderList.add(holder);
        product.setHolders(holderList);

        /*updating user owned product in user product repository*/
        UserProduct userProduct =  new UserProduct();
        userProduct.setProductId(product.getId());
        userProduct.setName(product.getName());
        userProduct.setUser(user);
        userProductRepository.save(userProduct);
        /*
        returning string of "product saved"
        */

        return productService.saveProduct(product);
    }



    @GetMapping("/getUserProduct")
    public List<UserProductDto> getuserProduct(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String  userName = jwtUtility.getUsernameFromToken(token);
        User user = userRepository.findByUserName(userName).get();
        return dtoMapper.userProductToUserProductDtoList(userProductRepository.findAllByUser(user));
    }
}