package com.example.onlinecoursebackend.db.entity.quiz;

import com.example.onlinecoursebackend.db.entity.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;                          // Qaysi test

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;                          // Kim yechgan

    private Integer attemptNumber;              // Nechanchi urinish (1, 2, 3...)

    private Double score;                       // Olingan ball (%)

    private Integer totalQuestions;             // Umumiy savollar soni
    private Integer correctAnswers;             // To'g'ri javoblar soni
    private Integer wrongAnswers;               // Noto'g'ri javoblar soni
    private Integer skippedAnswers;             // O'tkazib yuborilgan savollar soni

    private Boolean passed;                     // O'tdimi yoki yo'qmi

    private LocalDateTime startedAt;            // Testni boshlagan vaqt
    private LocalDateTime finishedAt;           // Testni tugatgan vaqt

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizAttemptAnswer> answers = new ArrayList<>();  // Har bir savolga javob

    private LocalDateTime createdAt = LocalDateTime.now();

    // ========================
    //       Getters/Setters
    // ========================

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getAttemptNumber() { return attemptNumber; }
    public void setAttemptNumber(Integer attemptNumber) { this.attemptNumber = attemptNumber; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }

    public Integer getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Integer correctAnswers) { this.correctAnswers = correctAnswers; }

    public Integer getWrongAnswers() { return wrongAnswers; }
    public void setWrongAnswers(Integer wrongAnswers) { this.wrongAnswers = wrongAnswers; }

    public Integer getSkippedAnswers() { return skippedAnswers; }
    public void setSkippedAnswers(Integer skippedAnswers) { this.skippedAnswers = skippedAnswers; }

    public Boolean getPassed() { return passed; }
    public void setPassed(Boolean passed) { this.passed = passed; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getFinishedAt() { return finishedAt; }
    public void setFinishedAt(LocalDateTime finishedAt) { this.finishedAt = finishedAt; }

    public List<QuizAttemptAnswer> getAnswers() { return answers; }
    public void setAnswers(List<QuizAttemptAnswer> answers) { this.answers = answers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}