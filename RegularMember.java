public class RegularMember extends GymMember
{
    private final int attendanceLimit=30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource ;
    private String plan;
    private double price;
    
    RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate,String referralSource){
        // this is done so that re-initialization of object is not required for instance this.id=id
        // grabbing it from the parent class itself using super keyword
        super(id,name,location,phone,email,gender,DOB,membershipStartDate); 
        this.referralSource= referralSource;
        this.isEligibleForUpgrade= false;
        this.plan= "basic";
        this.price= 6500;
        this.removalReason="";
        
    }
    //GETTER
    public final int getAttendanceLimit(){
        return this.attendanceLimit;
    }
    public boolean getIsEligibleForUpgrade(){
        return this.isEligibleForUpgrade;
    }
    public String getRemovalReason(){
        return this.removalReason;
    }
    public String getReferralSource(){
        return this.referralSource;
    }
    public String getPlan(){
        return this.plan;
    }
    public double getPrice(){
        return this.price;
    }
    // Implemented from the abstract method of the abstract class
    @Override
    public void markAttendance(){
        attendance= attendance+1;
        loyaltyPoints+=5;
          if (this.attendance >= this.attendanceLimit) {
            isEligibleForUpgrade = true;
        }
    }
    public int getAttendance(){
       return attendance;
    }

    public double getPlanPrice(String plan){  // gives the price for each plan
        switch(plan.toLowerCase()){
            case "basic":
                return 6500;
            case "standard":
                 return 12500;
            case "delux":
                return 18500;
            default:
                return -1.00;
                
        }
    }
  public String upgradePlan(String plan) {
    if (!this.isEligibleForUpgrade) {
        return "Member is not eligible for an upgrade.";
    }
    if (plan.equalsIgnoreCase(this.plan)) {
        return "You are already subscribed to this plan.";
    }
    double newPrice = getPlanPrice(plan);
    if (newPrice == -1) {
        return "Invalid plan selected.";
    }
    this.plan = plan;  // Update plan
    this.price = newPrice; // Assign correct new price
    return "Plan upgraded successfully to " + this.plan + " with price " + this.price;
}    
    public void revertRegularMember(String removalReason){
        //super.resetMember(); only in the overriden case
        resetMember();
        isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price= 6500;
        this.referralSource=referralSource;
        this.removalReason=removalReason;
    }
    @Override
    public void display(){
        super.display();
        System.out.println("The plan is: " + this.plan);
        System.out.println("The price is: " + this.price);
        if (removalReason.equals("")==false) {
            System.out.println("The removal reason is: " + this.removalReason);
        }
    }
}
   

    

