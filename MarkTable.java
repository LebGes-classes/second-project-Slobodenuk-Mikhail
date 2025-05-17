import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkTable {
    static Map<String, List<Map<String, String>>> workbook;
    static ArrayList<ArrayList<Integer>> markTable = new ArrayList<>();
    static BaseOfID baseOfID;

    public MarkTable(Map<String, List<Map<String, String>>> workbook, BaseOfID baseOfID) {
        this.workbook = workbook;
        this.baseOfID = baseOfID;
    }

    public void createTable(int cntStudents, int cntSubjects) {
        List<Map<String, String>> arrMarks = workbook.get("Marks");
        for (int i = 1; i <= cntStudents; i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for (int j = 1; j <= cntSubjects; j++) {
                boolean noMark = true;
                for (Map<String, String> mark : arrMarks) {
                    if (  (Integer.parseInt(mark.get("StudentsID")) == i) && (Integer.parseInt(mark.get("SubjectsID")) == j)){
                        newRow.add(Integer.parseInt(mark.get("Mark")));
                        noMark = false;
                    }
                }
                if (noMark) {
                    newRow.add(0);
                }


            }
            markTable.add(newRow);
        }
    }

    public void changeMark(int studentsID, int subjectsID, int mark) {
        markTable.get(studentsID - 1).set(subjectsID - 1, mark);
    }

    public void addStudent() {
        ArrayList<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < markTable.getFirst().size(); j++) {
                newRow.add(0);
            }
        markTable.add(newRow);
    }

    public void addSubject() {
        for (ArrayList<Integer> integers : markTable) {
            integers.add(0);
        }
    }

    public static void removeStudent(int id) {
        markTable.remove(id - 1);
    }

    public static void removeSubject(int id) {
        for (int i = 0; i < baseOfID.mapPlace.size(); i++) {
            markTable.get(i).remove(id - 1);
        }
    }

    public ArrayList<ArrayList<Integer>> getMarkTable() {
        return markTable;
    }
}
