package com.michaelliu.kotlin.module.repository.content

/**
 * Description:
 *
 * Created by liuguoquan on 2017/11/3 17:53.
 */

class RepositoryCondition {
    lateinit var q: String
    lateinit var sort: String
    lateinit var order: String
    var page: Int = 0
    var limit: Int = 10
}