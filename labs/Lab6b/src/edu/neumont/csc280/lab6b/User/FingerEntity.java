package edu.neumont.csc280.lab6b.User;

import javax.persistence.*;

/**
 * Created by blakerollins on 11/17/14.
 */
public class FingerEntity {

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
    private UserEntity user;

    @Column
    @Enumerated
    private FingerType type;

    public FingerEntity(Double length, int joints, FingerType type, UserEntity user) {
        this.length = length;
        this.joints = joints;
        this.type = type;
        this.user = user;
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

    public FingerType getType() {
        return this.type;
    }

    public UserEntity getUser() {
        return user;
    }

    public FingerEntity withUser(UserEntity user) {
        return new FingerEntity(this.getLength(), this.getJoints(), this.getType(), user);
    }
}
