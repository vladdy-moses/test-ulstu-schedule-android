package ru.ulstu_team.ulstuschedule.ui.common;

import java.util.List;

import ru.ulstu_team.ulstuschedule.data.model.Lesson;
import ru.ulstu_team.ulstuschedule.ui.base.MvpView;

public interface TeacherScheduleMvpView extends MvpView {

    void showSchedule(List<Lesson> lessons);

    void showEmptySchedule();

    void showError();

}
