import LzTable from './src/main.vue';

/* istanbul ignore next */
LzTable.install = function(Vue) {
    Vue.component(LzTable.name, LzTable);
};

export default LzTable;
