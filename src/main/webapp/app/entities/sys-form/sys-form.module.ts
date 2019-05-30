import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysFormComponent,
    SysFormDetailComponent,
    SysFormUpdateComponent,
    SysFormDeletePopupComponent,
    SysFormDeleteDialogComponent,
    sysFormRoute,
    sysFormPopupRoute
} from './';

const ENTITY_STATES = [...sysFormRoute, ...sysFormPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysFormComponent,
        SysFormDetailComponent,
        SysFormUpdateComponent,
        SysFormDeleteDialogComponent,
        SysFormDeletePopupComponent
    ],
    entryComponents: [SysFormComponent, SysFormUpdateComponent, SysFormDeleteDialogComponent, SysFormDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysFormModule {}
