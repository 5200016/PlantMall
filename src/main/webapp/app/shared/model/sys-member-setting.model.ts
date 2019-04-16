import { Moment } from 'moment';

export interface ISysMemberSetting {
    id?: number;
    growthProportion?: number;
    reviewGrowthValue?: number;
    checkGrowthValue?: number;
    integralProportion?: number;
    reviewIntegralValue?: number;
    checkIntegralValue?: number;
    integralProportionCash?: number;
    createTime?: Moment;
    updateTime?: Moment;
}

export class SysMemberSetting implements ISysMemberSetting {
    constructor(
        public id?: number,
        public growthProportion?: number,
        public reviewGrowthValue?: number,
        public checkGrowthValue?: number,
        public integralProportion?: number,
        public reviewIntegralValue?: number,
        public checkIntegralValue?: number,
        public integralProportionCash?: number,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
