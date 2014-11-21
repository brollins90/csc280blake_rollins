package edu.neumont.csc280.lab6.auth;

import javax.persistence.*;

/**
 * Created by blakerollins on 11/17/14.
 */
public class Finger {

    @Id
    @Column(name = "finger_id")
    @GeneratedValue(generator = "finger_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "finger_id_seq", sequenceName = "finger_id_seq")
    private Long id;

    @Column
    private Double length;

    @Column
    private int joints;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private User user;

    @Column
    @Enumerated
    private FingerType type;

    public Finger(Double length, int joints) {
        this.length = length;
        this.joints = joints;
    }

    public Long getId() {
        return id;
    }

    public Double getLength() {
        return length;
    }

    public int getJoints() {
        return joints;
    }

    public User getUser() {
        return user;
    }
}
