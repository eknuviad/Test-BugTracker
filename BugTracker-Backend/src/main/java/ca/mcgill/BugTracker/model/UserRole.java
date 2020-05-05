/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.BugTracker.model;
import java.util.*;
import java.sql.Date;

// line 17 "../../../../BugTracker-Backend.ump"
public class UserRole
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum InvitationStatus { NewInvite, Denied, Accepted }
  public enum TicketStatus { Todo, InProgress, Done }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Attributes
  private String password;
  private String userName;

  //UserRole Associations
  private User user;
  private List<Project> projects;
  private List<Invitation> invitations;
  private List<Ticket> tickets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRole(String aPassword, String aUserName, User aUser)
  {
    password = aPassword;
    userName = aUserName;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create userRole due to user");
    }
    projects = new ArrayList<Project>();
    invitations = new ArrayList<Invitation>();
    tickets = new ArrayList<Ticket>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUserName()
  {
    return userName;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetMany */
  public Project getProject(int index)
  {
    Project aProject = projects.get(index);
    return aProject;
  }

  public List<Project> getProjects()
  {
    List<Project> newProjects = Collections.unmodifiableList(projects);
    return newProjects;
  }

  public int numberOfProjects()
  {
    int number = projects.size();
    return number;
  }

  public boolean hasProjects()
  {
    boolean has = projects.size() > 0;
    return has;
  }

  public int indexOfProject(Project aProject)
  {
    int index = projects.indexOf(aProject);
    return index;
  }
  /* Code from template association_GetMany */
  public Invitation getInvitation(int index)
  {
    Invitation aInvitation = invitations.get(index);
    return aInvitation;
  }

  public List<Invitation> getInvitations()
  {
    List<Invitation> newInvitations = Collections.unmodifiableList(invitations);
    return newInvitations;
  }

  public int numberOfInvitations()
  {
    int number = invitations.size();
    return number;
  }

  public boolean hasInvitations()
  {
    boolean has = invitations.size() > 0;
    return has;
  }

  public int indexOfInvitation(Invitation aInvitation)
  {
    int index = invitations.indexOf(aInvitation);
    return index;
  }
  /* Code from template association_GetMany */
  public Ticket getTicket(int index)
  {
    Ticket aTicket = tickets.get(index);
    return aTicket;
  }

  public List<Ticket> getTickets()
  {
    List<Ticket> newTickets = Collections.unmodifiableList(tickets);
    return newTickets;
  }

  public int numberOfTickets()
  {
    int number = tickets.size();
    return number;
  }

  public boolean hasTickets()
  {
    boolean has = tickets.size() > 0;
    return has;
  }

  public int indexOfTicket(Ticket aTicket)
  {
    int index = tickets.indexOf(aTicket);
    return index;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    //Must provide user to userRole
    if (aUser == null)
    {
      return wasSet;
    }

    //user already at maximum (3)
    if (aUser.numberOfUserRoles() >= User.maximumNumberOfUserRoles())
    {
      return wasSet;
    }
    
    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      boolean didRemove = existingUser.removeUserRole(this);
      if (!didRemove)
      {
        user = existingUser;
        return wasSet;
      }
    }
    user.addUserRole(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProjects()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Project addProject(String aName, int aId)
  {
    return new Project(aName, aId, this);
  }

  public boolean addProject(Project aProject)
  {
    boolean wasAdded = false;
    if (projects.contains(aProject)) { return false; }
    UserRole existingUserRole = aProject.getUserRole();
    boolean isNewUserRole = existingUserRole != null && !this.equals(existingUserRole);
    if (isNewUserRole)
    {
      aProject.setUserRole(this);
    }
    else
    {
      projects.add(aProject);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProject(Project aProject)
  {
    boolean wasRemoved = false;
    //Unable to remove aProject, as it must always have a userRole
    if (!this.equals(aProject.getUserRole()))
    {
      projects.remove(aProject);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addProjectAt(Project aProject, int index)
  {  
    boolean wasAdded = false;
    if(addProject(aProject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProjects()) { index = numberOfProjects() - 1; }
      projects.remove(aProject);
      projects.add(index, aProject);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProjectAt(Project aProject, int index)
  {
    boolean wasAdded = false;
    if(projects.contains(aProject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProjects()) { index = numberOfProjects() - 1; }
      projects.remove(aProject);
      projects.add(index, aProject);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProjectAt(aProject, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfInvitations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Invitation addInvitation(InvitationStatus aInvStatus, int aId, Project aProject)
  {
    return new Invitation(aInvStatus, aId, this, aProject);
  }

  public boolean addInvitation(Invitation aInvitation)
  {
    boolean wasAdded = false;
    if (invitations.contains(aInvitation)) { return false; }
    UserRole existingUserRole = aInvitation.getUserRole();
    boolean isNewUserRole = existingUserRole != null && !this.equals(existingUserRole);
    if (isNewUserRole)
    {
      aInvitation.setUserRole(this);
    }
    else
    {
      invitations.add(aInvitation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInvitation(Invitation aInvitation)
  {
    boolean wasRemoved = false;
    //Unable to remove aInvitation, as it must always have a userRole
    if (!this.equals(aInvitation.getUserRole()))
    {
      invitations.remove(aInvitation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addInvitationAt(Invitation aInvitation, int index)
  {  
    boolean wasAdded = false;
    if(addInvitation(aInvitation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInvitations()) { index = numberOfInvitations() - 1; }
      invitations.remove(aInvitation);
      invitations.add(index, aInvitation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInvitationAt(Invitation aInvitation, int index)
  {
    boolean wasAdded = false;
    if(invitations.contains(aInvitation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInvitations()) { index = numberOfInvitations() - 1; }
      invitations.remove(aInvitation);
      invitations.add(index, aInvitation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInvitationAt(aInvitation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Ticket addTicket(TicketStatus aTicketStatus, String aDescription, Date aStartDate, Date aEndDate, Project aP)
  {
    return new Ticket(aTicketStatus, aDescription, aStartDate, aEndDate, this, aP);
  }

  public boolean addTicket(Ticket aTicket)
  {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) { return false; }
    UserRole existingUserRole = aTicket.getUserRole();
    boolean isNewUserRole = existingUserRole != null && !this.equals(existingUserRole);
    if (isNewUserRole)
    {
      aTicket.setUserRole(this);
    }
    else
    {
      tickets.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(Ticket aTicket)
  {
    boolean wasRemoved = false;
    //Unable to remove aTicket, as it must always have a userRole
    if (!this.equals(aTicket.getUserRole()))
    {
      tickets.remove(aTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(Ticket aTicket, int index)
  {  
    boolean wasAdded = false;
    if(addTicket(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(Ticket aTicket, int index)
  {
    boolean wasAdded = false;
    if(tickets.contains(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeUserRole(this);
    }
    for(int i=projects.size(); i > 0; i--)
    {
      Project aProject = projects.get(i - 1);
      aProject.delete();
    }
    for(int i=invitations.size(); i > 0; i--)
    {
      Invitation aInvitation = invitations.get(i - 1);
      aInvitation.delete();
    }
    for(int i=tickets.size(); i > 0; i--)
    {
      Ticket aTicket = tickets.get(i - 1);
      aTicket.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "," +
            "userName" + ":" + getUserName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}