<template>


    <div id="app">
    <img alt="Vue logo" src="./assets/log.png">
    <LineChart :chart-data="datacollection" :options="dataOption"></LineChart>
    <button @click="fillData()">Create</button>
</div>



</template>

<script>
import LineChart from './components/LineChart.vue'
import axios from 'axios'
export default {
    name: 'App',
    components: {
        LineChart
    },
    metaInfo: {
      title: 'WeatherApp',
    },
    data() {
        return {
            datacollection: {
                xLabels: ["2020-12-10", "2020-12-11"],
                datasets: [{
                    label: "keyword 1",
                    data: [{
                        x: "2020-12-10",
                        y: 12
                    }, {
                        x: "2020-12-11",
                        y: 19
                    }]
                }]
            },
            dataOption: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }],
                    xAxes: [{
                        type: "category",
                        position: "bottom"
                    }]
                }
            }
        }
    },
    mounted() {

        this.fillData()
    },
    methods: {
        fillData() {
            axios
                .get("http://localhost:8081/city/almaty", {
                    crossdomain: true
                })
                .then(response => {
                    this.datacollection = response.data;
                    console.log(response.data);
                })
                .catch(function (e) {
                    this.error = e;
                });
        }

    }

}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
