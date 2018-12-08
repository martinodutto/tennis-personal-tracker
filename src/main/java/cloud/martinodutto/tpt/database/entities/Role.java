package cloud.martinodutto.tpt.database.entities;

public class Role {

    private int roleId;

    private String role;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        sb.append("roleId=").append(roleId);
        sb.append(", role=").append(role);
        sb.append("]");
        return sb.toString();
    }
}
