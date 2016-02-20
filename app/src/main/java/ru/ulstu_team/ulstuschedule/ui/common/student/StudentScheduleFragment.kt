package ru.ulstu_team.ulstuschedule.ui.common.student

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.schedule.*
import ru.ulstu_team.ulstuschedule.R
import ru.ulstu_team.ulstuschedule.data.model.Lesson
import ru.ulstu_team.ulstuschedule.ui.base.BaseFragment
import ru.ulstu_team.ulstuschedule.ui.common.StickyListScheduleAdapter
import java.util.*
import javax.inject.Inject

class StudentScheduleFragment() : BaseFragment(), StudentScheduleMvpView {

    @Inject
    internal lateinit var mAdapter: StickyListScheduleAdapter
    @Inject
    internal lateinit var mPresenter: StudentSchedulePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getStudentScheduleComponent().inject(this)
        return inflater.inflate(R.layout.schedule, container, false)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
        mPresenter.loadSchedule()

        srlRefresh.setOnRefreshListener { mPresenter.loadSchedule() }
    }

    override fun showSchedule(lessons: List<Lesson>) {
        mAdapter.setLessons(lessons, false)
        slTeacherLessons.adapter = mAdapter
        srlRefresh.isRefreshing = false
    }

    override fun showEmptySchedule() {
        mAdapter.setLessons(ArrayList<Lesson>(), false)
        srlRefresh.isRefreshing = false
    }

    override fun showError() {
        Snackbar.make(view, "Возниикла ошибка", Snackbar.LENGTH_LONG)
                .setAction("Повторить", { reload() })
                .show()
    }

    override fun reload() {
        mPresenter.reload()
    }

    companion object {
        val TAG = "StudentScheduleFragment"
    }
}
