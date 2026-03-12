import java.util.*;

class Profile {

    String name;
    String role;

    ArrayList<String> skills = new ArrayList<>();
    LinkedList<String> projects = new LinkedList<>();
    Stack<String> history = new Stack<>();
    PriorityQueue<String> tasks = new PriorityQueue<>();
    HashMap<String, String> peopleSkills = new HashMap<>();

    Profile(String name, String role){
        this.name = name;
        this.role = role;
    }

    void displayProfile(){
        System.out.println("\n----- Profile -----");
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
        System.out.println("Skills: " + skills);
        System.out.println("Projects: " + projects);
        System.out.println("-------------------");
    }

    void addSkill(String skill){
        skills.add(skill);
        history.push("Added Skill: " + skill);
    }

    void addProject(String project){
        projects.add(project);
        history.push("Added Project: " + project);
    }

    void addTask(String task){
        tasks.add(task);
        history.push("Added Task: " + task);
    }

    void addPerson(String person, String skill){
        peopleSkills.put(person, skill);
        history.push("Added Person: " + person + " (" + skill + ")");
    }

    void searchSkill(String skill){

        for(String s : skills){
            if(s.equalsIgnoreCase(skill)){
                System.out.println("Skill found: " + s);
                return;
            }
        }

        System.out.println("Skill not found.");
    }

    void searchProject(String project){

        for(String p : projects){
            if(p.equalsIgnoreCase(project)){
                System.out.println("Project found: " + p);
                return;
            }
        }

        System.out.println("Project not found.");
    }

    void searchPerson(String name){

        if(peopleSkills.containsKey(name)){
            System.out.println("\nPerson Details:");
            System.out.println("Name: " + name);
            System.out.println("Skill: " + peopleSkills.get(name));
        }
        else{
            System.out.println("Person not found.");
        }
    }

    void showTasks(){
        System.out.println("\nTasks in Queue:");
        for(String t : tasks){
            System.out.println(t);
        }
    }

    void showHistory(){
        System.out.println("\nActivity History:");
        for(String h : history){
            System.out.println(h);
        }
    }
}

public class PortfolioDSA {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Profile aditya = new Profile("Aditya Vardhan","Web Developer");

        int choice;

        do{

            System.out.println("\n--- Portfolio Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Add Skill");
            System.out.println("3. Add Project");
            System.out.println("4. Search Skill");
            System.out.println("5. Search Project");
            System.out.println("6. Add Task");
            System.out.println("7. View Tasks");
            System.out.println("8. Add Person with Skill");
            System.out.println("9. Search Person (View Details)");
            System.out.println("10. View Activity History");
            System.out.println("11. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    aditya.displayProfile();
                    break;

                case 2:
                    System.out.print("Enter skill: ");
                    aditya.addSkill(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter project: ");
                    aditya.addProject(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter skill to search: ");
                    aditya.searchSkill(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter project to search: ");
                    aditya.searchProject(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Enter task: ");
                    aditya.addTask(sc.nextLine());
                    break;

                case 7:
                    aditya.showTasks();
                    break;

                case 8:
                    System.out.print("Enter person name: ");
                    String person = sc.nextLine();
                    System.out.print("Enter skill: ");
                    String skill = sc.nextLine();
                    aditya.addPerson(person, skill);
                    break;

                case 9:
                    System.out.print("Enter person name to search: ");
                    aditya.searchPerson(sc.nextLine());
                    break;

                case 10:
                    aditya.showHistory();
                    break;

                case 11:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        }while(choice != 11);

        sc.close();
    }
}