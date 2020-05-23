import LzTabs from './src/main.vue';

/* istanbul ignore next */
LzTabs.install = function(Vue) {
    Vue.component(LzTabs.name, LzTabs);
};

export default LzTabs;
