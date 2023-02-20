package io;

import java.util.List;
import Modelo.Category;
import Modelo.Node;
import Modelo.Product;
import Modelo.Token;
import Modelo.User;
import Modelo.UserLogIn;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;


public interface ProductoApiService {

    @GET ("/api/product")
    Call<List<Product>> getProductos();

    @GET ("/api/category")
    Call<List<Category>> getCategorias();

    @GET ("api/product/{id}")
    Call<Product> getProductoPorId(@Path("id") long prodId);

    @POST ("/api/user/signup")
    Call <User> postUser(@Body User u);

    @POST ("/api/token/generate-token")
    Call <Token> getToken(@Body UserLogIn u);

    @GET ("/api/node")
    Call<List<Node>> getNodes();

}
