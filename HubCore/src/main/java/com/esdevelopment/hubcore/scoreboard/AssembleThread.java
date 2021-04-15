package com.esdevelopment.hubcore.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collections;
import java.util.List;

public class AssembleThread extends Thread {

    private Assemble assemble;

    AssembleThread(Assemble assemble) {
        this.assemble = assemble;
        this.start();
    }

    @Override
    public void run() {
        while(true) {
            //Tick
            try {
                tick();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Thread Sleep
            try {
                sleep(assemble.getTicks() * 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        for (Player player : this.assemble.getPlugin().getServer().getOnlinePlayers()) {
            try {
                AssembleBoard board = this.assemble.getBoards().get(player.getUniqueId());

                if (board == null) {
                    continue;
                }

                Scoreboard scoreboard = board.getScoreboard();
                Objective objective = board.getObjective();


                if (scoreboard == null || objective == null) {
                    continue;
                }

                String title = ChatColor.translateAlternateColorCodes('&', this.assemble.getAdapter().getTitle(player));

                if (!objective.getDisplayName().equals(title)) {
                    objective.setDisplayName(title);
                }

                List<String> newLines = this.assemble.getAdapter().getLines(player);


                if (newLines == null || newLines.isEmpty()) {
                    board.getEntries().forEach(AssembleBoardEntry::remove);
                    board.getEntries().clear();
                } else {
                    if (this.assemble.getAdapter().getLines(player).size() > 15) {
                        newLines = this.assemble.getAdapter().getLines(player).subList(0, 15);
                    }

                    if (!this.assemble.getAssembleStyle().isDecending()) {
                        Collections.reverse(newLines);
                    }

                    if (board.getEntries().size() > newLines.size()) {
                        for (int i = newLines.size(); i < board.getEntries().size(); i++) {
                            AssembleBoardEntry entry = board.getEntryAtPosition(i);

                            if (entry != null) {
                                entry.remove();
                            }
                        }
                    }

                    int cache = this.assemble.getAssembleStyle().getStartNumber();
                    for (int i = 0; i < newLines.size(); i++) {
                        AssembleBoardEntry entry = board.getEntryAtPosition(i);

                        String line = ChatColor.translateAlternateColorCodes('&', newLines.get(i));

                        if (entry == null) {
                            entry = new AssembleBoardEntry(board, line, i);
                        }

                        entry.setText(line);
                        entry.setup();
                        entry.send(
                                this.assemble.getAssembleStyle().isDecending() ? cache-- : cache++
                        );
                    }
                }

                if (player.getScoreboard() != scoreboard && !assemble.isHook()) {
                    player.setScoreboard(scoreboard);
                }
            } catch(Exception e) {
                throw new AssembleException("There was an error updating " + player.getName() + "'s scoreboard.");
            }
        }
    }
}
