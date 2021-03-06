package com.sjjybsgj.dao.jobinfo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public JobinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("JOB_ID is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("JOB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(String value) {
            addCriterion("JOB_ID =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(String value) {
            addCriterion("JOB_ID <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(String value) {
            addCriterion("JOB_ID >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_ID >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(String value) {
            addCriterion("JOB_ID <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(String value) {
            addCriterion("JOB_ID <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLike(String value) {
            addCriterion("JOB_ID like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotLike(String value) {
            addCriterion("JOB_ID not like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<String> values) {
            addCriterion("JOB_ID in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<String> values) {
            addCriterion("JOB_ID not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(String value1, String value2) {
            addCriterion("JOB_ID between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(String value1, String value2) {
            addCriterion("JOB_ID not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("JOB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("JOB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("JOB_NAME =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("JOB_NAME <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("JOB_NAME >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_NAME >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("JOB_NAME <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_NAME <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("JOB_NAME like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("JOB_NAME not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("JOB_NAME in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("JOB_NAME not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("JOB_NAME between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("JOB_NAME not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andNowStageIsNull() {
            addCriterion("NOW_STAGE is null");
            return (Criteria) this;
        }

        public Criteria andNowStageIsNotNull() {
            addCriterion("NOW_STAGE is not null");
            return (Criteria) this;
        }

        public Criteria andNowStageEqualTo(String value) {
            addCriterion("NOW_STAGE =", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageNotEqualTo(String value) {
            addCriterion("NOW_STAGE <>", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageGreaterThan(String value) {
            addCriterion("NOW_STAGE >", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageGreaterThanOrEqualTo(String value) {
            addCriterion("NOW_STAGE >=", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageLessThan(String value) {
            addCriterion("NOW_STAGE <", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageLessThanOrEqualTo(String value) {
            addCriterion("NOW_STAGE <=", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageLike(String value) {
            addCriterion("NOW_STAGE like", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageNotLike(String value) {
            addCriterion("NOW_STAGE not like", value, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageIn(List<String> values) {
            addCriterion("NOW_STAGE in", values, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageNotIn(List<String> values) {
            addCriterion("NOW_STAGE not in", values, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageBetween(String value1, String value2) {
            addCriterion("NOW_STAGE between", value1, value2, "nowStage");
            return (Criteria) this;
        }

        public Criteria andNowStageNotBetween(String value1, String value2) {
            addCriterion("NOW_STAGE not between", value1, value2, "nowStage");
            return (Criteria) this;
        }

        public Criteria andAllStageIsNull() {
            addCriterion("ALL_STAGE is null");
            return (Criteria) this;
        }

        public Criteria andAllStageIsNotNull() {
            addCriterion("ALL_STAGE is not null");
            return (Criteria) this;
        }

        public Criteria andAllStageEqualTo(String value) {
            addCriterion("ALL_STAGE =", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageNotEqualTo(String value) {
            addCriterion("ALL_STAGE <>", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageGreaterThan(String value) {
            addCriterion("ALL_STAGE >", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageGreaterThanOrEqualTo(String value) {
            addCriterion("ALL_STAGE >=", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageLessThan(String value) {
            addCriterion("ALL_STAGE <", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageLessThanOrEqualTo(String value) {
            addCriterion("ALL_STAGE <=", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageLike(String value) {
            addCriterion("ALL_STAGE like", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageNotLike(String value) {
            addCriterion("ALL_STAGE not like", value, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageIn(List<String> values) {
            addCriterion("ALL_STAGE in", values, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageNotIn(List<String> values) {
            addCriterion("ALL_STAGE not in", values, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageBetween(String value1, String value2) {
            addCriterion("ALL_STAGE between", value1, value2, "allStage");
            return (Criteria) this;
        }

        public Criteria andAllStageNotBetween(String value1, String value2) {
            addCriterion("ALL_STAGE not between", value1, value2, "allStage");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andIsEndIsNull() {
            addCriterion("IS_END is null");
            return (Criteria) this;
        }

        public Criteria andIsEndIsNotNull() {
            addCriterion("IS_END is not null");
            return (Criteria) this;
        }

        public Criteria andIsEndEqualTo(String value) {
            addCriterion("IS_END =", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotEqualTo(String value) {
            addCriterion("IS_END <>", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndGreaterThan(String value) {
            addCriterion("IS_END >", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndGreaterThanOrEqualTo(String value) {
            addCriterion("IS_END >=", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLessThan(String value) {
            addCriterion("IS_END <", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLessThanOrEqualTo(String value) {
            addCriterion("IS_END <=", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLike(String value) {
            addCriterion("IS_END like", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotLike(String value) {
            addCriterion("IS_END not like", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndIn(List<String> values) {
            addCriterion("IS_END in", values, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotIn(List<String> values) {
            addCriterion("IS_END not in", values, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndBetween(String value1, String value2) {
            addCriterion("IS_END between", value1, value2, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotBetween(String value1, String value2) {
            addCriterion("IS_END not between", value1, value2, "isEnd");
            return (Criteria) this;
        }

        public Criteria andStatesIsNull() {
            addCriterion("STATES is null");
            return (Criteria) this;
        }

        public Criteria andStatesIsNotNull() {
            addCriterion("STATES is not null");
            return (Criteria) this;
        }

        public Criteria andStatesEqualTo(Integer value) {
            addCriterion("STATES =", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotEqualTo(Integer value) {
            addCriterion("STATES <>", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesGreaterThan(Integer value) {
            addCriterion("STATES >", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATES >=", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesLessThan(Integer value) {
            addCriterion("STATES <", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesLessThanOrEqualTo(Integer value) {
            addCriterion("STATES <=", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesIn(List<Integer> values) {
            addCriterion("STATES in", values, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotIn(List<Integer> values) {
            addCriterion("STATES not in", values, "states");
            return (Criteria) this;
        }

        public Criteria andStatesBetween(Integer value1, Integer value2) {
            addCriterion("STATES between", value1, value2, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotBetween(Integer value1, Integer value2) {
            addCriterion("STATES not between", value1, value2, "states");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jobinfo
     *
     * @mbg.generated do_not_delete_during_merge Wed May 30 21:57:09 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jobinfo
     *
     * @mbg.generated Wed May 30 21:57:09 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}