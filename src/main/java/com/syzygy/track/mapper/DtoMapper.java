package com.syzygy.track.mapper;

import com.syzygy.track.dto.productdto.HolderDto;
import com.syzygy.track.dto.productdto.ProductDto;
import com.syzygy.track.dto.productdto.ProductListDto;
import com.syzygy.track.dto.userdto.UpdateUserDto;
import com.syzygy.track.dto.userdto.UserDto;
import com.syzygy.track.dto.userdto.UserProductDto;
import com.syzygy.track.entity.Holder;
import com.syzygy.track.entity.Product;
import com.syzygy.track.model.User;
import com.syzygy.track.model.UserData;
import com.syzygy.track.model.UserProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {
    @Autowired
    private ModelMapper mapper;
    public HolderDto userToHolderDto(User user) {
        HolderDto holderDto = mapper.map(user,HolderDto.class);
        return holderDto;
    }

    public HolderDto holderToholderDto(Holder holder) {
        HolderDto holderDto = mapper.map(holder,HolderDto.class);
        return holderDto;
    }

    public UserDto userTouserDto(User user) {
        UserDto userDto = mapper.map(user,UserDto.class);
        return userDto;
    }

    public UpdateUserDto userDataToUpdateuserDto(UserData userData) {
       UpdateUserDto updateUserDto = mapper.map(userData,UpdateUserDto.class);
        return updateUserDto;
    }

    public UserProductDto userProducToUserProductDto(UserProduct userProduct) {
        UserProductDto updateUserDto = mapper.map(userProduct,UserProductDto.class);
        return updateUserDto;
    }

    public List<UserProductDto> userProductToUserProductDtoList(List<UserProduct> userProductList) {
        return	userProductList.stream().map(x -> userProducToUserProductDto(x)).collect(Collectors.toList());
    }

    public UserData updateUsertoUserData(UpdateUserDto updateUserDto){
        UserData userData = mapper.map(updateUserDto,UserData.class);
        return userData;
    }

    public ProductDto productToProductDto(Product product) {
        ProductDto productDto = mapper.map(product,ProductDto.class);
        return productDto;
    }

    public List<ProductDto> productListToDto(List<Product> products) {
        return	products.stream().map(x -> productToProductDto(x)).collect(Collectors.toList());
    }

    public ProductListDto productToProductListDto(Product product) {
        ProductListDto productListDto = mapper.map(product,ProductListDto.class);
        return productListDto;
    }
    public List<ProductListDto> productListDtoListToDto(List<Product> products) {
        return	products.stream().map(x -> productToProductListDto(x)).collect(Collectors.toList());
    }
}
