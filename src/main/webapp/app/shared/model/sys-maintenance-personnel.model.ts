import { Moment } from 'moment';
import { ISysOrder } from 'app/shared/model//sys-order.model';

export interface ISysMaintenancePersonnel {
    id?: number;
    openid?: string;
    name?: string;
    phone?: string;
    createTime?: Moment;
    updateTime?: Moment;
    orders?: ISysOrder[];
}

export class SysMaintenancePersonnel implements ISysMaintenancePersonnel {
    constructor(
        public id?: number,
        public openid?: string,
        public name?: string,
        public phone?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public orders?: ISysOrder[]
    ) {}
}
