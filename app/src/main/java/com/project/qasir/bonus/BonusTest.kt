package com.project.qasir.bonus

import java.util.*

class BonusTest {

    companion object {

        fun isBalancedBrackets(string: String): Boolean {
            val deque: Deque<Char> = LinkedList()

            for (ch in string.toCharArray()) {
                if (ch == '{' || ch == '[' || ch == '(') {
                    deque.addFirst(ch)
                } else {
                    if (!deque.isEmpty()
                            && (deque.peekFirst() == '{' && ch == '}'
                                    || deque.peekFirst() == '[' && ch == ']'
                                    || deque.peekFirst() == '(' && ch == ')')) {
                        deque.removeFirst()
                    } else {
                        return false
                    }
                }
            }

            return true
        }

        fun getCamelCaseCount(string: String): Int {
            var count = 1
            val strArray = string.toCharArray()

            for (i in 1 until string.length - 1) {
                if (strArray[i] == strArray[i].toUpperCase()) {
                    count++
                }
            }

            return count
        }

    }

}