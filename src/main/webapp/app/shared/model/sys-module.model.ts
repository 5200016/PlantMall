import { Moment } from 'moment';
import { ISysClassify } from 'app/shared/model//sys-classify.model';

export interface ISysModule {
    id?: number;
    name?: string;
    icon?: string;
    image?: string;
    imageDisable?: number;
    type?: number;
    styleType?: number;
    homeMenu?: number;
    homeBottom?: number;
    path?: string;
    sort?: number;
    createTime?: Moment;
    updateTime?: Moment;
    classify?: ISysClassify;
}

export class SysModule implements ISysModule {
    constructor(
        public id?: number,
        public name?: string,
        public icon?: string,
        public image?: string,
        public imageDisable?: number,
        public type?: number,
        public styleType?: number,
        public homeMenu?: number,
        public homeBottom?: number,
        public path?: string,
        public sort?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public classify?: ISysClassify
    ) {}
}
