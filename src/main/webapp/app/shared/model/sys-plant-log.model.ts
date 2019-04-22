import { Moment } from 'moment';

export interface ISysPlantLog {
    id?: number;
    name?: string;
    image?: string;
    description?: any;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysPlantLog implements ISysPlantLog {
    constructor(
        public id?: number,
        public name?: string,
        public image?: string,
        public description?: any,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
