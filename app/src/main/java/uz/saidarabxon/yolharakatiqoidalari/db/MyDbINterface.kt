package uz.saidarabxon.yolharakatiqoidalari.db

import uz.saidarabxon.yolharakatiqoidalari.models.Models

interface MyDbINterface {

    fun addLabel(models: Models)
    fun getAllLabel(): ArrayList<Models>
    fun editLabel(models: Models)
    fun deleteLabel(models: Models)
}