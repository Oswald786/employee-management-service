package com.example.adaptors;

import com.example.Mapper.RolesMapper;
import com.example.entities.RolesEntity;
import com.example.exceptionHandling.ExecptionHandlingModels.RoleNotFoundException;
import com.example.models.RolesModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.relation.Role;
import java.util.List;

@NoArgsConstructor
public class AuthServiceDatabaseRolesAdaptor {

    Logger logger = LoggerFactory.getLogger(AuthServiceDatabaseRolesAdaptor.class);

    @Inject
    EntityManager entityManager;

    @Inject
    RolesMapper rolesMapper;

    //Create a new role
    public void createRole(RolesModel roleToCreate){
        entityManager.persist(rolesMapper.toEntity(roleToCreate));
        entityManager.flush();
        logger.info("Role Created Successfully");
    }



    //retrieve roles creator

    //retrive role by role name
    public RolesModel retrieveRoleByRoleName(String roleName ) {
        try {
            RolesEntity returnedEntity = entityManager.createQuery("SELECT r FROM RolesEntity r WHERE r.roleName = :roleName", RolesEntity.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();
            return rolesMapper.toModel(returnedEntity);
        }catch (NoResultException e){
            throw new RoleNotFoundException("Role name not found name given: " + roleName + ".");
        }
    }

    //retrive role by id
    public RolesModel retrieveRoleById(Integer roleId) {
        RolesEntity returnedEntity = entityManager.find(RolesEntity.class, roleId);
        if (returnedEntity == null) {
            throw new RoleNotFoundException("role with id: " + roleId + " not found.");
        }
        return rolesMapper.toModel(returnedEntity);
    }

    //retrieve all roles
    public List<RolesModel> retrieveAllRoles() {
        return entityManager.createQuery("SELECT r FROM RolesEntity r", RolesEntity.class).getResultList().stream().map(rolesMapper::toModel).toList();
    }

    public void deleteRoleById(Integer roleId) {
        RolesEntity roleToDelete = entityManager.find(RolesEntity.class, roleId);
        if (roleToDelete == null) {
            throw new RoleNotFoundException("role by id not found user gave id: " + roleId + ".");
        }
        entityManager.remove(roleToDelete);
        entityManager.flush();
        logger.info("Role Deleted Successfully");
    }

    public void deleteRoleByName(String roleName) {
        int rowsDeleted = entityManager.createQuery(
                        "DELETE FROM RolesEntity r WHERE r.roleName = :roleName")
                .setParameter("roleName", roleName)
                .executeUpdate();

        if (rowsDeleted == 0) {
            throw new RoleNotFoundException("Role with name '" + roleName + "' not found");
        }

        logger.info("Role deleted successfully");
    }

    public void deleteAllRoles() {
        int rowsDeleted = entityManager.createQuery("DELETE FROM RolesEntity r").executeUpdate();
        logger.info("All Roles Deleted Successfully Roles deleted: " + rowsDeleted + ".");
    }

    //update role name
    public void updateRoleName(Integer roleId, String newRoleName) {
        RolesEntity roleToUpdate = entityManager.find(RolesEntity.class, roleId);
        if (roleToUpdate == null) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        roleToUpdate.setRoleName(newRoleName);
        entityManager.merge(roleToUpdate);
        logger.info("Role name updated successfully");
    }

    //update role description
    public void updateRoleDescription(Integer roleId, String newRoleDescription) {
        RolesEntity roleToUpdate = entityManager.find(RolesEntity.class, roleId);
        if (roleToUpdate == null) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        roleToUpdate.setDescription(newRoleDescription);
    }
}
