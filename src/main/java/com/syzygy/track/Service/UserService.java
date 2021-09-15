package com.syzygy.track.Service;

import com.syzygy.track.Dto.ProductDTO.HolderDto;
import com.syzygy.track.Dto.ProductDTO.ProductDetailDto;
import com.syzygy.track.Entity.Holder;
import com.syzygy.track.Entity.Product;
import com.syzygy.track.Mapper.DtoMapper;
import com.syzygy.track.Repository.ProductRepository;
import com.syzygy.track.Repository.UserDataRepository;
import com.syzygy.track.Repository.UserProductRepository;
import com.syzygy.track.Repository.UserRepository;
import com.syzygy.track.model.User;
import com.syzygy.track.model.UserProduct;
import com.syzygy.track.utils.JWTUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JWTUtilty jwtUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private UserProductRepository userProductRepository;
    @Autowired
    private DtoMapper dtoMapper;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public String Scan(ProductDetailDto productDetailDto, User user){
        String id = productDetailDto.getId();
        Product product = productRepository.findById(id).get();
       List<Holder> holders= product.getHolders();
       HolderDto holderDto = dtoMapper.userToHolderDto(user) ;
       Holder holder = new Holder();
       holder.setUserName(holderDto.getUserName());
       holder.setRole(holderDto.getRole());
       holder.setPhoneNo(holderDto.getPhoneNo());
       holder.setComments(productDetailDto.getComments());
       holder.setLocation(productDetailDto.getLocation());
       holders.add(holder);
       UserProduct userProduct =  new UserProduct();
       userProduct.setProductId(product.getId());
       userProduct.setName(product.getName());
       userProduct.setUser(user);
       userProductRepository.save(userProduct);
       productRepository.save(product);
        return "updated";
}}
