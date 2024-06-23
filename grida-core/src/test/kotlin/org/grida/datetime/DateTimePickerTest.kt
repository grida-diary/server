package org.grida.datetime

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class DateTimePickerTest :
    BehaviorSpec({

        Given("지금 시간과 날짜를 구하려 할 때") {
            val dateTimePicker = DateTimePicker()

            When("now()를 호출하면") {
                val actual = dateTimePicker.now()

                Then("시간과 날짜를 알려준다.") {
                    actual.year shouldBeInRange IntRange(Int.MIN_VALUE, Int.MAX_VALUE)
                    actual.monthValue shouldBeInRange IntRange(1, 12)

                    actual.hour shouldBeInRange IntRange(0, 23)
                    actual.minute shouldBeInRange IntRange(0, 59)
                }
            }
        }

        Given("지금 달의 범위를 구하려 할 때") {
            val dateTimePicker = DateTimePicker()

            When("thisMonthRange()를 호출하면") {
                val actual = dateTimePicker.thisMonthRange()

                Then("이번 달의 시작 날짜와 종료 날짜를 알려준다") {
                    actual.start.dayOfMonth shouldBe 1
                    actual.end.dayOfMonth shouldBeInRange IntRange(28, 31)
                    actual.start.month.value shouldBe actual.end.month.value
                }
            }
        }

        Given("틀정 달의 범위를 구하려 할 때") {
            val dateTimePicker = DateTimePicker()

            When("윤년이 아닌 2월을 입력하면") {
                val year = 2023
                val month = 2
                val actual = dateTimePicker.monthOfYearRange(year, month)

                Then("끝나는 날이 28일이다.") {
                    actual.end.dayOfMonth shouldBe 28
                }
            }

            When("윤년인 2월을 입력하면") {
                val year = 2024
                val month = 2
                val actual = dateTimePicker.monthOfYearRange(year, month)

                Then("끝나는 날이 28일이다.") {
                    actual.end.dayOfMonth shouldBe 29
                }
            }

            When("1월을 입력하면") {
                val year = 2024
                val month = 1
                val actual = dateTimePicker.monthOfYearRange(year, month)

                Then("끝나는 날이 31일이다.") {
                    actual.end.dayOfMonth shouldBe 31
                }
            }

            When("4월을 입력하면") {
                val year = 2024
                val month = 4
                val actual = dateTimePicker.monthOfYearRange(year, month)

                Then("끝나는 날이 30일이다.") {
                    actual.end.dayOfMonth shouldBe 30
                }
            }
        }
    })
