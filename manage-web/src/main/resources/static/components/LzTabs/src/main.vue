<template>
    <div class="tabs-content">
        <div class="page-tabs">
                <span class="tabs_nav_prev" @click="prevTab">
                    <i class="el-icon-d-arrow-left"></i>
                </span>
            <div class="content-tabs">
                <div class="content-tabs-container">
                    <ul id="tabUl">
                        <li v-for="(item,index) in tabList" :class="{'active': isActive(item.id)}" :key="index">
                            <a href="#" :class="{'active': isActive(item.id)}" @click="activeTab(item.id)">{{item.name}}</a>
                            <span v-if="item.allowClose==true" class="tabs-close" @click="removeTab(item.id)"><i class="el-icon-close"></i></span>
                        </li>
                    </ul>
                </div>
            </div>
            <span class="tabs_nav_next" @click="nextTab">
                    <i class="el-icon-d-arrow-right"></i>
                </span>
            <div class="tabs-close-box">
                <el-dropdown @command="handleCommand">
                    <span>
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="A">定位当前选项卡</el-dropdown-item>
                        <el-dropdown-item command="B">关闭当前选项卡</el-dropdown-item>
                        <el-dropdown-item divided command="C">关闭其他选项卡</el-dropdown-item>
                        <el-dropdown-item command="D">关闭全部选项卡</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
        <div class="tab-content">
            <div class="tab-pane" v-for="(item,index) in tabList" :key="index" :style="{'display':(item.id==activeId?'block':'none')}">
                <iframe :src="item.url" class="tabs-frame" frameborder="0" :name="item.id" scrolling="auto"></iframe>
            </div>
        </div>
    </div>
</template>
<script>
    import Vue from 'vue';
    import ElementUI from 'element-ui';
    import 'element-ui/lib/theme-chalk/index.css';
    Vue.use(ElementUI);

    export default {
        name: 'LzTabs',
        props: {
            tabs: Array,
            active: String
        },
        data: function () {
            return {
                activeId: this.active,
                tabList: this.tabs,
                leftestShowIndex: 0
            }
        },
        methods: {
            isActive(id) {
                return this.activeId == id;
            },
            activeTab(id) {
                if (this.activeId != id) {
                    this.activeId = id;
                    this.$emit('update-active', this.activeId);
                }
            },
            getActive() {
                for (var i = 0; i < this.tabList.length; i++) {
                    if (this.activeId == this.tabList[i].id) {
                        return this.tabList[i];
                    }
                }
            },
            getActiveIndex() {
                for (var i = 0; i < this.tabList.length; i++) {
                    if (this.activeId == this.tabList[i].id) {
                        return i;
                    }
                }
            },
            updateTabList(tabList) {
                this.tabList = tabList;
                this.$emit('update-tabs', this.tabList);
            },
            prevTab() {
                //显示的最左边的li
                var leftestShowLi;
                var totalLiWidth = 0;
                var contentTabs = document.querySelector(".content-tabs");
                //显示的宽度
                var totalWidth = contentTabs.offsetWidth - 30 - 30 - 30;
                var ul = document.querySelector("#tabUl");
                var childNodes = ul.childNodes;
                for (var i = 0; i < childNodes.length; i++) {
                    if (i >= this.leftestShowIndex) {
                        totalLiWidth = totalLiWidth + childNodes[i].offsetWidth;
                    }
                    if (i == this.leftestShowIndex) {
                        leftestShowLi = childNodes[i];
                    }
                }
                var marginLeft = ul.offsetLeft;
                if (totalLiWidth > totalWidth) {
                    var contentTabsContainer = document.querySelector(".content-tabs-container");
                    contentTabsContainer.style.marginLeft = marginLeft - leftestShowLi.offsetWidth + 'px';
                    this.leftestShowIndex++;
                }
            },
            nextTab() {
                //显示的最左边的li
                if (this.leftestShowIndex > 0) {
                    var rightestHideLi;
                    var ul = document.querySelector("#tabUl");
                    var childNodes = ul.childNodes;
                    for (var i = 0; i < childNodes.length; i++) {
                        if (i == this.leftestShowIndex) {
                            var m = i-1;
                            rightestHideLi = childNodes[m];
                        }
                    }
                    var marginLeft = ul.offsetLeft;
                    var contentTabsContainer = document.querySelector(".content-tabs-container");
                    contentTabsContainer.style.marginLeft = marginLeft + rightestHideLi.offsetWidth + 'px';
                    this.leftestShowIndex--;
                }
            },
            removeTab(id) {
                if (id == this.activeId) {
                    var index = this.getActiveIndex();
                    if (index === this.tabList.length - 1) {
                        this.activeTab(this.tabList[index - 1].id);
                    } else {
                        this.activeTab(this.tabList[index + 1].id);
                    }
                }

                var newTabList = this.tabList.filter(item => {
                    return item.id != id;
                });
                this.updateTabList(newTabList)
            },
            isExists(id) {
                for (var n = 0; n < this.tabList.length; n++) {
                    if (id == this.tabList[n].id) {
                        return true;
                    }
                }
                return false;
            },
            addTab(item) {
                var isExist = this.isExists(item.id);
                if (isExist) {
                    this.activeTab(item.id);
                } else {
                    this.tabList.push({
                        id: item.id,
                        name: item.name,
                        url: item.url,
                        allowClose: item.allowClose ? item.allowClose : false

                    });
                    this.activeTab(item.id);
                    this.locationCurrent();
                    this.$emit('updateTabs', this.tabList);
                }
            },
            locationCurrent() {
                //当前激活选项卡及右侧的总宽度
                var rightTotalWidth = 0;
                //当前激活选项卡左侧的总宽度（不包括当前）
                var leftTotalWidth = 0;
                var contentTabs = document.querySelector(".content-tabs");
                //可显示的宽度（此为固定值）
                var totalWidth = contentTabs.offsetWidth - 30 - 30 - 30;
                //当前激活的li,从0开始计数
                var index = this.getActiveIndex();
                //变为从1开始
                index = index+1;
                var ul = document.querySelector("#tabUl");
                var childNodes = ul.childNodes;
                //当新增选项卡时，index为真实的，但是此时childNodes还没有增加，此处默认取第一个li的长度
                if(index>childNodes.length){
                    rightTotalWidth = rightTotalWidth + childNodes[0].offsetWidth;
                }
                for (var i = 0; i < childNodes.length; i++) {
                    if (i >= (index-1)) {
                        rightTotalWidth = rightTotalWidth + childNodes[i].offsetWidth;
                    } else {
                        leftTotalWidth = leftTotalWidth + childNodes[i].offsetWidth;
                    }
                }
                var contentTabsContainer = document.querySelector(".content-tabs-container");
                //若totalWidth<=rightTotalWidth，将当前选中的选项卡作为第一个显示的选项卡
                if (totalWidth <= rightTotalWidth) {
                    contentTabsContainer.style.marginLeft = 32 - leftTotalWidth + 'px';
                    this.leftestShowIndex = index;
                } else {
                    var rightIndex;
                    for (var k = index-2; k >= 0; k--) {
                        if (rightTotalWidth + childNodes[k].offsetWidth >= totalWidth) {
                            rightIndex = k;
                            break;
                        } else {
                            rightTotalWidth = rightTotalWidth + childNodes[k].offsetWidth
                        }
                    }
                    leftTotalWidth = 0;
                    //当childNodes还没增加时，默认多移动一个选项卡
                    if(index>childNodes.length){
                        rightIndex++;
                    }
                    for (var j = 0; j <= rightIndex; j++) {
                        leftTotalWidth = leftTotalWidth + childNodes[j].offsetWidth;
                    }
                    contentTabsContainer.style.marginLeft = 32 - leftTotalWidth + 'px';
                    this.leftestShowIndex = rightIndex + 1;
                }
            },
            closeCurrent() {
                this.removeTab(this.activeId);
            },
            closeOthers() {
                var newTabList = this.tabList.filter(item => {
                    return item.id == this.activeId || item.allowClose == undefined || item.allowClose == false;
                });
                this.updateTabList(newTabList);
            },
            closeAll() {
                var newTabList = this.tabList.filter(item => {
                    return item.id == this.activeId || item.allowClose == undefined || item.allowClose == false;
                });
                this.updateTabList(newTabList)
                if (this.tabList.length > 0) {
                    this.activeTab(this.tabList[0].id);
                }
            },
            handleCommand(command) {
                if ("A" == command) {
                    this.locationCurrent();
                } else if ("B" == command) {
                    this.closeCurrent();
                } else if ("C" == command) {
                    this.closeOthers();
                } else {
                    this.closeAll();
                }
            }
        }
    }
</script>
<style scoped>
    #tabUl{
        margin: 0px;
        padding: 0px;
    }
    .tabs-content{
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0px;
        bottom: 0px;
    }
    .page-tabs{
        height: 32px;
        width: 100%;
        background: #fafafa;
        line-height: 30px;
        position: relative;
    }

    .content-tabs{
        width: 100%;
        position: relative;
        height: 32px;
        background: #fafafa;
        line-height: 30px;
        overflow: hidden;
    }
    .page-tabs ul li{
        list-style: none;
        float: left;
        padding-left: 10px;
        padding-right: 10px;
        height: 28px;
        border: 1px solid #c7cbd2;
        background: #fff;
        border-radius: 5px;
        font-size: 12px;
    }
    .page-tabs ul li span{
        font-size: 14px;
    }
    .page-tabs ul li:hover{
        background: #F3F7F9;
    }
    .page-tabs ul li.active{
        background: #5cb6ff;
        color: #fff;
    }
    .tabs_nav_prev{
        position: absolute;
        cursor: pointer;
        line-height: 30px;
        font-size: 12px;
        color: #909399;
        left: 0px;
        width: 30px;
        text-align: center;
        z-index: 2;
        background: #ffffff;
    }
    .tabs_nav_next{
        position: absolute;
        cursor: pointer;
        line-height: 30px;
        font-size: 12px;
        color: #909399;
        right: 30px;
        width: 30px;
        text-align: center;
        z-index: 2;
        top: 0px;
        background: #ffffff;
    }
    .tabs-close-box{
        position: absolute;
        top: 0px;
        right: 0px;
        width: 30px;
        line-height: 30px;
        text-align: center;
        background: #ffffff;
    }
    .tabs_nav_prev:hover,.tabs_nav_next:hover,.tabs-close-box:hover{
        background: #F3F7F9;
    }
    .page-tabs ul li a{
        text-decoration: none;
        color: #666;
    }
    .page-tabs ul li a.active{
        color: #fff;
    }
    .tabs-close{
        text-align: right;
    }
    .content-tabs-container{
        width: 100000px;
        margin-left: 32px;
        overflow: hidden;
        height: 32px;
        transition: margin-left 0.2s;
        -moz-transition: margin-left 0.2s;
        -webkit-transition: margin-left 0.2s;
        -o-transition: margin-left 0.2s;
    }
    .tab-content {
        height: calc(100% - 32px);
    }
    .tabs-frame{
        width:100%;
        /*height: calc(100% - 42px);*/
        height: 100%;
        position: relative;
        overflow-x: hidden;
        display: block;
    }
    .tab-pane{
        height: 100%;
    }
</style>
