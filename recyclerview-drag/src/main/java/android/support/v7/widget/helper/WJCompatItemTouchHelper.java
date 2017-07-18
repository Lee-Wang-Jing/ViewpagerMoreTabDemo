/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.support.v7.widget.helper;

/**
 * 作者：Created by WangJing on 2017/7/6.
 * 邮箱：wangjinggm@gmail.com
 * 描述：TODO
 * 最近修改：2017/7/6 10:11 by WangJing
 */
public class WJCompatItemTouchHelper extends ItemTouchHelper {

    public WJCompatItemTouchHelper(Callback callback) {
        super(callback);
    }

    /**
     * Developer callback which controls the behavior of ItemTouchHelper.
     *
     * @return {@link Callback}
     */
    public Callback getCallback() {
        return mCallback;
    }
}
