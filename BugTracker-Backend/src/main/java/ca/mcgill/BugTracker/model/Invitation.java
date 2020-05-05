/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.BugTracker.model;

// line 42 "../../../../BugTracker-Backend.ump"
public class Invitation
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum InvitationStatus { NewInvite, Denied, Accepted }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Invitation Attributes
  private InvitationStatus invStatus;
  private int id;

  //Invitation Associations
  private UserRole userRole;
  private Project project;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Invitation(InvitationStatus aInvStatus, int aId, UserRole aUserRole, Project aProject)
  {
    invStatus = aInvStatus;
    id = aId;
    boolean didAddUserRole = setUserRole(aUserRole);
    if (!didAddUserRole)
    {
      throw new RuntimeException("Unable to create invitation due to userRole");
    }
    boolean didAddProject = setProject(aProject);
    if (!didAddProject)
    {
      throw new RuntimeException("Unable to create invitation due to project");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInvStatus(InvitationStatus aInvStatus)
  {
    boolean wasSet = false;
    invStatus = aInvStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public InvitationStatus getInvStatus()
  {
    return invStatus;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template association_GetOne */
  public Project getProject()
  {
    return project;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUserRole(UserRole aUserRole)
  {
    boolean wasSet = false;
    if (aUserRole == null)
    {
      return wasSet;
    }

    UserRole existingUserRole = userRole;
    userRole = aUserRole;
    if (existingUserRole != null && !existingUserRole.equals(aUserRole))
    {
      existingUserRole.removeInvitation(this);
    }
    userRole.addInvitation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setProject(Project aProject)
  {
    boolean wasSet = false;
    if (aProject == null)
    {
      return wasSet;
    }

    Project existingProject = project;
    project = aProject;
    if (existingProject != null && !existingProject.equals(aProject))
    {
      existingProject.removeInvitation(this);
    }
    project.addInvitation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    UserRole placeholderUserRole = userRole;
    this.userRole = null;
    if(placeholderUserRole != null)
    {
      placeholderUserRole.removeInvitation(this);
    }
    Project placeholderProject = project;
    this.project = null;
    if(placeholderProject != null)
    {
      placeholderProject.removeInvitation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "invStatus" + "=" + (getInvStatus() != null ? !getInvStatus().equals(this)  ? getInvStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "project = "+(getProject()!=null?Integer.toHexString(System.identityHashCode(getProject())):"null");
  }
}