package com.example.adaptors;




import com.example.Mapper.RolePermissionsMapper;
import com.example.entities.RolePermissionsEntity;
import com.example.models.RolePermissionsModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@NoArgsConstructor
public class AuthServiceDatabaseRolePermissionsAdaptor {

    Logger logger = LoggerFactory.getLogger(AuthServiceDatabaseRolePermissionsAdaptor.class);

    @Inject
   EntityManager entityManager;

    @Inject
    RolePermissionsMapper rolePermissionsMapper;

    public void createRolePermissionLink (RolePermissionsModel rolePermissionsModel){
        //Set up the entity and persist it
        entityManager.persist(rolePermissionsMapper.toEntity(rolePermissionsModel));
        entityManager.flush();
        logger.info("Role-Permission Link Created Successfully");
    }

    public List<RolePermissionsModel> retrieveRolePermissionLinksUsingRoleId(Integer roleId){
        TypedQuery<RolePermissionsEntity> query = entityManager.createQuery("select rp from RolePermissionsEntity rp where rp.roleId = :roleId", RolePermissionsEntity.class);
        logger.info("Retrieving Role-Permission Links for Role Id: {}", roleId);
        query.setParameter("roleId", roleId);
        List<RolePermissionsEntity> result = query.getResultList();
        result.forEach(RolePermissionsEntity -> logger.info("Role-Permission Link Found: {}",RolePermissionsEntity.toString()));
        return result.stream().map(rolePermissionsMapper::toModel).toList();
    }

    public List<RolePermissionsModel> retrieveRolePermissionLinksUsingPermissionId(Integer permissionId){
        TypedQuery<RolePermissionsEntity> query = entityManager.createQuery("select rp from RolePermissionsEntity rp where rp.permissionId = :permissionId", RolePermissionsEntity.class);
        logger.info("Retrieving Role-Permission Links for Permission Id:{} ", permissionId);
        query.setParameter("permissionId", permissionId);
        List<RolePermissionsEntity> result = query.getResultList();
        result.forEach(RolePermissionsEntity -> logger.info("Role-Permission Link Found: {} ", RolePermissionsEntity.toString()));
        return result.stream().map(rolePermissionsMapper::toModel).toList();
    }

    public void deleteRolePermissionLink(Integer roleId, Integer permissionId){
        entityManager.createQuery("delete from RolePermissionsEntity where roleId = :roleId and permissionId = :permissionId")
                .setParameter("roleId", roleId).setParameter("permissionId", permissionId).executeUpdate();
        logger.info("Role-Permission Link Deleted Successfully");
    }

    public void deleteAllRolePermissionsForRole(Integer roleId){
        entityManager.createQuery("delete from RolePermissionsEntity where roleId = :roleId")
                .setParameter("roleId", roleId).executeUpdate();
        logger.info("All Role-Permission Links Deleted Successfully for Role Id: {}", roleId);
    }

}
