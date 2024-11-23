package com.rayan.messenger.rest.resources;

import java.util.List;

import com.rayan.messenger.rest.model.Message;
import com.rayan.messenger.rest.model.Profile;
import com.rayan.messenger.rest.service.MessageService;
import com.rayan.messenger.rest.service.ProfileService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/profiles") // top level path annotation
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private ProfileService service = new ProfileService();

    @GET
    public List<Profile> getAllProfiles() {

        return service.getAllProfiles();
    }

    @GET
    @Path("/{profileId}") // method level path annotation
    public Profile getProfile(@PathParam("profileId") String _id) {
        return service.getProfileById(_id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile insertProfile(Profile profile) {
        return service.insertProfile(profile);
    }

    @PUT
    @Path("/{profileId}")
    public Profile updateProfile(@PathParam("profileId") String _id, Profile profile) {
        profile.set_id(_id);
        return service.updateProfile(profile, _id);
    }

    @DELETE
    @Path("/{profileId}")
    public void deleteProfile(@PathParam("profileId") String _id) {
        service.deleteProfile(_id);
    }
}
