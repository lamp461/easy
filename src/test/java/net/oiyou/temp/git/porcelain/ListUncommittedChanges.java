package net.oiyou.temp.git.porcelain;

import net.oiyou.helper.CookbookHelper;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.IndexDiff;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author HuangBoo
 */
public class ListUncommittedChanges {
    public static void main(String[] args) throws IOException, GitAPIException {
        try (Repository repository = CookbookHelper.openGitCookbookRepository()) {
            System.out.println("Listing uncommitted changes:");
            try (Git git = new Git(repository)) {
                Status status = git.status().call();
                Set<String> conflicting = status.getConflicting();
                for(String conflict : conflicting) {
                    System.out.println("Conflicting: " + conflict);
                }

                Set<String> added = status.getAdded();
                for(String add : added) {
                    System.out.println("Added: " + add);
                }

                Set<String> changed = status.getChanged();
                for(String change : changed) {
                    System.out.println("Change: " + change);
                }

                Set<String> missing = status.getMissing();
                for(String miss : missing) {
                    System.out.println("Missing: " + miss);
                }

                Set<String> modified = status.getModified();
                for(String modify : modified) {
                    System.out.println("Modification: " + modify);
                }

                Set<String> removed = status.getRemoved();
                for(String remove : removed) {
                    System.out.println("Removed: " + remove);
                }

                Set<String> uncommittedChanges = status.getUncommittedChanges();
                for(String uncommitted : uncommittedChanges) {
                    System.out.println("Uncommitted: " + uncommitted);
                }

                Set<String> untracked = status.getUntracked();
                for(String untrack : untracked) {
                    System.out.println("Untracked: " + untrack);
                }

                Set<String> untrackedFolders = status.getUntrackedFolders();
                for(String untrack : untrackedFolders) {
                    System.out.println("Untracked Folder: " + untrack);
                }

                Map<String, IndexDiff.StageState> conflictingStageState = status.getConflictingStageState();
                for(Map.Entry<String, IndexDiff.StageState> entry : conflictingStageState.entrySet()) {
                    System.out.println("ConflictingState: " + entry);
                }
            }
        }
    }
}
