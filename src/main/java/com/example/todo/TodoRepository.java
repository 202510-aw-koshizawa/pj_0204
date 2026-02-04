package com.example.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 完了状態でフィルタリング
    List<Todo> findByCompleted(boolean completed);

    // タイトルで部分一致検索
    List<Todo> findByTitleContaining(String keyword);

    // 期限日が今日以前のもの
    List<Todo> findByDueDateLessThanEqual(LocalDate date);

    // 優先度で昇順ソート
    List<Todo> findAllByOrderByPriorityAsc();

    // 作成日時の新しい順でソート
    List<Todo> findAllByOrderByCreatedAtDesc();

    // @Queryの例: 完了かつ期限日が今日以前
    @Query("select t from Todo t where t.completed = :completed and t.dueDate <= :date")
    List<Todo> findCompletedAndDueDateBefore(@Param("completed") boolean completed, @Param("date") LocalDate date);
}
