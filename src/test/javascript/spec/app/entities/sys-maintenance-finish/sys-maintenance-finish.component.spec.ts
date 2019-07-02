/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenanceFinishComponent } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish.component';
import { SysMaintenanceFinishService } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish.service';
import { SysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

describe('Component Tests', () => {
    describe('SysMaintenanceFinish Management Component', () => {
        let comp: SysMaintenanceFinishComponent;
        let fixture: ComponentFixture<SysMaintenanceFinishComponent>;
        let service: SysMaintenanceFinishService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenanceFinishComponent],
                providers: []
            })
                .overrideTemplate(SysMaintenanceFinishComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMaintenanceFinishComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenanceFinishService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysMaintenanceFinish(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysMaintenanceFinishes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
