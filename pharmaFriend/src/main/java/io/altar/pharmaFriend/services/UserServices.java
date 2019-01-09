package  io.altar.pharmaFriend.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import  io.altar.pharmaFriend.business.UserBusiness;

import  io.altar.pharmaFriend.models.User;

@Path("user")
public class UserServices {

	@Inject
	UserBusiness userBusiness1;

	@GET
	@Path("consultbylogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultUser(@QueryParam("login") String login) {
		User user = userBusiness1.consultByLogin(login);
		if (user != null) {
			return Response.status(200).entity(user).build();
		}
		else{
			return Response.status(401).build();
		}
	}

	@GET
	@Path("consulttologin")
	@Produces(MediaType.APPLICATION_JSON)
	public User consultToLogin(@QueryParam("login") String login, @QueryParam("pass") String passWord) {

		return userBusiness1.consultToLogin(login, passWord);
	}

	@GET
	@Path("consultbyname")
	@Produces(MediaType.APPLICATION_JSON)
	public User consultMedicine(@QueryParam("medicineName") String name) {

		return userBusiness1.consultByName(name);
	}

	@GET
	@Path("consultid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User consultMedicineId(@PathParam("id") long id) {
		return userBusiness1.consultById(id);
	}

	@GET
	@Path("consultall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> consultAll() {
		return userBusiness1.consultAll();
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		userBusiness1.createUser(user);
		return user;
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(User user) {

		userBusiness1.updateUser(user);
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public void removeUser(@PathParam("id") long id) {
		userBusiness1.removeUserById(id);
	}

}
