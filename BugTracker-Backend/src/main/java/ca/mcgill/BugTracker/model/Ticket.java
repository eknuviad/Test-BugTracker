/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.BugTracker.model;
import java.sql.Date;

// line 60 "../../../../BugTracker-Backend.ump"
public class Ticket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TicketStatus { Todo, InProgress, Done }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  private TicketStatus ticketStatus;
  private String description;
  private Date startDate;
  private Date endDate;

  //Ticket Associations
  private UserRole userRole;
  private Project p;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(TicketStatus aTicketStatus, String aDescription, Date aStartDate, Date aEndDate, UserRole aUserRole, Project aP)
  {
    ticketStatus = aTicketStatus;
    description = aDescription;
    startDate = aStartDate;
    endDate = aEndDate;
    boolean didAddUserRole = setUserRole(aUserRole);
    if (!didAddUserRole)
    {
      throw new RuntimeException("Unable to create ticket due to userRole");
    }
    boolean didAddP = setP(aP);
    if (!didAddP)
    {
      throw new RuntimeException("Unable to create t due to p");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTicketStatus(TicketStatus aTicketStatus)
  {
    boolean wasSet = false;
    ticketStatus = aTicketStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public TicketStatus getTicketStatus()
  {
    return ticketStatus;
  }

  public String getDescription()
  {
    return description;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template association_GetOne */
  public Project getP()
  {
    return p;
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
      existingUserRole.removeTicket(this);
    }
    userRole.addTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setP(Project aP)
  {
    boolean wasSet = false;
    if (aP == null)
    {
      return wasSet;
    }

    Project existingP = p;
    p = aP;
    if (existingP != null && !existingP.equals(aP))
    {
      existingP.removeT(this);
    }
    p.addT(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    UserRole placeholderUserRole = userRole;
    this.userRole = null;
    if(placeholderUserRole != null)
    {
      placeholderUserRole.removeTicket(this);
    }
    Project placeholderP = p;
    this.p = null;
    if(placeholderP != null)
    {
      placeholderP.removeT(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ticketStatus" + "=" + (getTicketStatus() != null ? !getTicketStatus().equals(this)  ? getTicketStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "p = "+(getP()!=null?Integer.toHexString(System.identityHashCode(getP())):"null");
  }
}