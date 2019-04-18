import { Moment } from 'moment';

export interface ISysRecharge {
    id?: number;
    price?: number;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysRecharge implements ISysRecharge {
    constructor(public id?: number, public price?: number, public createTime?: Moment, public updateTime?: Moment) {}
}
