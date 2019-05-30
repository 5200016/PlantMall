import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysOrder } from 'app/shared/model//sys-order.model';

export interface ISysMaintenancePersonnel {
    id?: number;
    status?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    orders?: ISysOrder[];
}

export class SysMaintenancePersonnel implements ISysMaintenancePersonnel {
    constructor(
        public id?: number,
        public status?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public orders?: ISysOrder[]
    ) {}
}
