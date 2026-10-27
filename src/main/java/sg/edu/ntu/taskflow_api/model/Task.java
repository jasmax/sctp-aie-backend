package sg.edu.ntu.taskflow_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    /** Optional free-text description of the task. */
    private String description;
    /**
     * Lifecycle status matching the frontend values:
     * "todo" | "in-progress" | "done"
     * Defaults to "todo" on creation if not provided.
     */
    private String status;
    /**
     * Urgency level matching the frontend values:
     * "low" | "medium" | "high"
     * Defaults to "low" on creation if not provided.
     */
    private String priority;
}