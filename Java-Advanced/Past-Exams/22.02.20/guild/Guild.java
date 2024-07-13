package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private List<Player> roster;
    private String name;
    private int capacity;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player){
        if(this.roster.size() < this.capacity){
            roster.add(player);
        }
    }
    public int count(){
        return this.roster.size();
    }

    public boolean removePlayer(String name){

        Player player = roster.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(player != null){
            roster.remove(player);
            return true;
        }
        return false;
    }

    public void promotePlayer(String name){
        Player player = roster.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(player != null){
            player.setRank("Member");
        }
    }

    public void demotePlayer(String name){
        int index = findPlayerIndex(name);
        if(index != -1){
            roster.get(index).setRank("Trial");
        }
    }

    public Player[] kickPlayersByClass(String clazz){
        int count = 0;
        for (Player player : roster) {
            if(player.getClazz().equals(clazz)){
                count++;
            }
        }
        Player[] arr = new Player[count];
        int index = 0;
        for (Player player : roster) {
            if(player.getClazz().equals(clazz)){
                arr[index++] = player;
            }
        }
        for (Player player : arr) {
            roster.remove(player);
        }
        return arr;
    }

    private int findPlayerIndex(String name){

        for (int i = 0; i < this.roster.size(); i++) {
            if(this.roster.get(i).getName().equals(name)){
                return i;
            }
        }
        return  -1;
    }

    public String report(){
        StringBuilder builder = new
                StringBuilder("Players in the guild: " + name + ":" + System.lineSeparator());
        for (Player player : roster) {
            builder.append(player.toString())
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
