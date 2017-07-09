package model.dialogue

import jquery.JQuery
import org.w3c.dom.Element

/*
 * Copyright (c) 2017.
 *
 * This file is part of ProcessManager.
 *
 * ProcessManager is free software: you can redistribute it and/or modify it under the terms of version 3 of the
 * GNU Lesser General Public License as published by the Free Software Foundation.
 *
 * ProcessManager is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with ProcessManager.  If not,
 * see <http://www.gnu.org/licenses/>.
 */

/**
 * Activity set up, currently only small choice but nicely fits together
 *
 * @module Dialogue
 */

external interface ActivityStatic {
    operator fun invoke(node: JQuery, storage: Array<Any?>):Activity
}

external interface Activity {
    fun init()
    fun render(content: Element)
    fun initChange()
    fun initListeners()
    fun initSave()
    fun initCollapse()
}

@JsModule("model/dialogue/activity")
@JsNonModule
external val activity: ActivityStatic = definedExternally
