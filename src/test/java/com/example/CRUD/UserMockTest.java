package com.example.CRUD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.isA;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.CoreMatchers.is;
import com.example.CRUD.dtos.UserDto;
import com.example.CRUD.models.UserModel;
import com.example.CRUD.repository.UserRepository;
import com.example.CRUD.service.UserService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserMockTest {

    @Autowired
    private WebApplicationContext context;


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private    UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testMockMvcGetOneUser() throws Exception{
        UserDto userDto=new UserDto();
        userDto.setCpf("111.111.111-11");
        userDto.setPassword("123");

        UserModel userModel=new UserModel();
        userModel.setId(1l);
        userModel.setCpf("111.111.111-11");
        userModel.setPassword("123");

        BDDMockito.when(userService.detalhar(1l)).thenReturn(Optional.of(userModel));

        MockHttpServletRequestBuilder request= MockMvcRequestBuilders
                .get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.cpf", is("111.111.111-11")));
    }
    
    @Test
    public void testMockMvcGetAllUser() throws Exception{

        UserModel userModel=new UserModel();
        userModel.setId(1l);
        userModel.setCpf("111.111.111-11");
        userModel.setPassword("123");

        List<UserModel> usersModel= Arrays.asList(userModel);

        BDDMockito.when(userService.acharTodos()).thenReturn(usersModel);

        MockHttpServletRequestBuilder request= MockMvcRequestBuilders
                .get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
  

        mockMvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
            .andExpect(jsonPath("$[0].cpf", is("111.111.111-11")))
            .andExpect(jsonPath("$[?(@.cpf=='111.111.111-11')]").exists());
    }

    @Test
    public void testMockMvcCreateUser() throws Exception{

        UserModel userModel=new UserModel();
        userModel.setId(1l);
        userModel.setCpf("111.111.111-11");
        userModel.setPassword("123");

        UserDto userDto=new UserDto();
        userDto.setCpf("111.111.111-11");
        userDto.setAtivo(true);

        String json=new ObjectMapper().writeValueAsString(userDto);

        BDDMockito.when(userService.create(userDto)).thenReturn(userDto);
        

        MockHttpServletRequestBuilder request= MockMvcRequestBuilders
                .post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
  

            mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.cpf", is("111.111.111-11")));
    }

    @Test
    public void testMockMvcDeleteUser() throws Exception{

        UserModel userModel=new UserModel();
        userModel.setId(1l);
        userModel.setCpf("111.111.111-11");
        userModel.setPassword("123");

        UserDto userDto=new UserDto();
        userDto.setCpf("111.111.111-11");
        userDto.setAtivo(true);

        String json=new ObjectMapper().writeValueAsString(userDto);

        BDDMockito.when(userService.delete(1l)).thenReturn(userDto);

        BDDMockito.when(userRepository.findById(1l)).thenReturn(Optional.of(userModel));

        MockHttpServletRequestBuilder request= MockMvcRequestBuilders
                .delete("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
  

            mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.cpf", is("111.111.111-11")));
    }

    @Test
    public void testMockMvcUpdateUser() throws Exception{

        UserModel userModel=new UserModel();
        userModel.setId(1l);
        userModel.setCpf("111.111.111-11");
        userModel.setPassword("123");

        UserDto userDto=new UserDto();
        userDto.setCpf("111.111.111-11");
        userDto.setAtivo(true);

        String json=new ObjectMapper().writeValueAsString(userDto);

        BDDMockito.when(userService.update(1l,userDto)).thenReturn(userDto);

        BDDMockito.when(userRepository.findById(1l)).thenReturn(Optional.of(userModel));

        MockHttpServletRequestBuilder request= MockMvcRequestBuilders
                .put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
  

            mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.cpf", is("111.111.111-11")));
    }
}
