import { Moment } from 'moment';
import { ISysOrder } from 'app/shared/model//sys-order.model';

export interface ISysMaintenanceFinish {
    id?: number;
    time?: string;
    finishTime?: string;
    url?: string;
    createTime?: Moment;
    updateTime?: Moment;
    order?: ISysOrder;
}

export class SysMaintenanceFinish implements ISysMaintenanceFinish {
    constructor(
        public id?: number,
        public time?: string,
        public finishTime?: string,
        public url?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public order?: ISysOrder
    ) {}
}
