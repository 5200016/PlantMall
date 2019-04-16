import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysClassifyComponent,
    SysClassifyDetailComponent,
    SysClassifyUpdateComponent,
    SysClassifyDeletePopupComponent,
    SysClassifyDeleteDialogComponent,
    sysClassifyRoute,
    sysClassifyPopupRoute
} from './';

const ENTITY_STATES = [...sysClassifyRoute, ...sysClassifyPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysClassifyComponent,
        SysClassifyDetailComponent,
        SysClassifyUpdateComponent,
        SysClassifyDeleteDialogComponent,
        SysClassifyDeletePopupComponent
    ],
    entryComponents: [SysClassifyComponent, SysClassifyUpdateComponent, SysClassifyDeleteDialogComponent, SysClassifyDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysClassifyModule {}
