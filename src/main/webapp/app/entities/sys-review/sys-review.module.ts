import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysReviewComponent,
    SysReviewDetailComponent,
    SysReviewUpdateComponent,
    SysReviewDeletePopupComponent,
    SysReviewDeleteDialogComponent,
    sysReviewRoute,
    sysReviewPopupRoute
} from './';

const ENTITY_STATES = [...sysReviewRoute, ...sysReviewPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysReviewComponent,
        SysReviewDetailComponent,
        SysReviewUpdateComponent,
        SysReviewDeleteDialogComponent,
        SysReviewDeletePopupComponent
    ],
    entryComponents: [SysReviewComponent, SysReviewUpdateComponent, SysReviewDeleteDialogComponent, SysReviewDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysReviewModule {}
