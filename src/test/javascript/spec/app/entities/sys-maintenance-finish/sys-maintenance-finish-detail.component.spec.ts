/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenanceFinishDetailComponent } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish-detail.component';
import { SysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

describe('Component Tests', () => {
    describe('SysMaintenanceFinish Management Detail Component', () => {
        let comp: SysMaintenanceFinishDetailComponent;
        let fixture: ComponentFixture<SysMaintenanceFinishDetailComponent>;
        const route = ({ data: of({ sysMaintenanceFinish: new SysMaintenanceFinish(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenanceFinishDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysMaintenanceFinishDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMaintenanceFinishDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysMaintenanceFinish).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
