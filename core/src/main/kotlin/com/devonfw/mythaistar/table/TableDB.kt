package com.devonfw.mythaistar.table

import com.devonfw.mythaistar.user.User


/**
 * Created by zaptsiau on 15.11.2017.
 */
object TableDB {

    private var tables : ArrayList<Table> = ArrayList<Table>()


    fun addTable(table : Table){
        tables.add(table)
    }

    fun removeTable(table : Table){
        tables.remove(table)
    }

    fun removeTablebyOwner(owner : User){
        var tmpTable : Table? = null
        for(table in tables){
            if(table.owner == owner){
                tmpTable = table
                break
            }
            if(tmpTable != null){
                removeTable(tmpTable)
            }
        }
    }

    fun findTableByOwner(owner : User):Boolean {
        var tmpTable : Table? = null
        for(table in tables){
            if(table.owner == owner){
                return true
            }
        }
        return false
    }
}