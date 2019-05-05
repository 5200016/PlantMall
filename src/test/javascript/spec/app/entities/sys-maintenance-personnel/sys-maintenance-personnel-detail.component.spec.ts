/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenancePersonnelDetailComponent } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel-detail.component';
import { SysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

describe('Component Tests', () => {
    describe('SysMaintenancePersonnel Management Detail Component', () => {
        let comp: SysMaintenancePersonnelDetailComponent;
        let fixture: ComponentFixture<SysMaintenancePersonnelDetailComponent>;
        const route = ({ data: of({ sysMaintenancePersonnel: new SysMaintenancePersonnel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenancePersonnelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysMaintenancePersonnelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMaintenancePersonnelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysMaintenancePersonnel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
