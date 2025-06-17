
/**
 * @author (Suvashish Shrestha)
 * @version (March 7, 2025)
 */
public abstract class GymMember
{
   protected int id;
   protected String name;
   protected String location;
   protected String phone;
   protected String email;
   protected String gender;
   protected String DOB;
   protected String membershipStartDate;
   protected int attendance;
   protected double loyaltyPoints;
   protected boolean activeStatus;
   
   public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB,String membershipStartDate){
       this.attendance= 0;
       this.loyaltyPoints= 0.00;
       this.activeStatus=false;
       this.id = id; // id parameter value passed in to instance variable id
       this.name= name;
       this.location= location;
       this.phone = phone;
       this.email= email;
       this.membershipStartDate=membershipStartDate;
       this.gender= gender;
       this.DOB= DOB;
       //Java assigns default values to instance variables with their datatype
       
   }
   //Getter
   public int getId(){
       return this.id;
   }
   public String getName(){
       return this.name;
   } 
   public int getAttendance(){
       return this.attendance;
   }
   public double getLoyaltyPoints(){
       return this.loyaltyPoints;
   }
   public boolean getActiveStatus(){
       return this.activeStatus;
   }
   public String getLocation(){
       return this.location;
   }
   public String getPhone(){
       return this.phone;
   }
   public String getEmail(){
       return this.email;
   }
   public String getMembershipStartDate(){
       return this.membershipStartDate;
   }
    public String getDOB(){
       return this.DOB;
   }
   public String getGender() {
       return this.gender; 
   }
   
    //Methods to be called in the subclass
    // Abstract method
   abstract void markAttendance();
   
   // Active status false by default
   public void activeMembership(){
       if (activeStatus ==false){
       activeStatus = true;
    }
       else{
           System.out.println("It's already activated");
       
    }
   }
   public void deactivateMembership(){
       if(activeStatus){ // Ensure membership is active
           activeStatus = false;
           System.out.println("Deactivated Successfully");
       }else{
           System.out.println("Membership already inactive.Activate it first.");
       }
       
   }
   public void  resetMember(){
       activeStatus= false;
       attendance=0;
       loyaltyPoints=0.0;
   }
   public void display(){   
       System.out.println("The user id is : " + this.id);
       System.out.println("The user name is : " + this.name);
       System.out.println("The user location is : " + this.location);
       System.out.println("The user phone is : " + this.phone);
       System.out.println("The user email is : " + this.email);
       System.out.println("The user gender is : " + this.gender);
       System.out.println("The user DOB is : " + this.DOB);
       System.out.println("The user start date  is : " + this.membershipStartDate);
       System.out.println("The user attendance is : " + this.attendance);
       System.out.println("The user loyalty points is : " + this.loyaltyPoints );
       System.out.println("The user status is : " + this.activeStatus );
   }

}
