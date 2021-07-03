package student.system;

public class CommandHandler {
    private final StudentsRepository repository;

    public CommandHandler() {
        this.repository = new StudentsRepository();
    }

    public String handleCommand(String[]tokens){
        if (tokens[0].equals("Create")) {
            String name=tokens[1];
            int age= Integer.parseInt(tokens[2]);
            double grade= Double.parseDouble(tokens[3]);

            Student student=new Student(name,age,grade);
            if (!repository.contains(name)) {
                this.repository.add(student);
            }
            return null;
        }else if (tokens[0].equals("Show")) {
            String name=tokens[1];
            if (repository.contains(name)) {
                return repository.get(name).getInfo();
            }else {
                return null;
            }
        }
        return tokens[0];
    }
}
