/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberLevelComponent } from 'app/entities/sys-member-level/sys-member-level.component';
import { SysMemberLevelService } from 'app/entities/sys-member-level/sys-member-level.service';
import { SysMemberLevel } from 'app/shared/model/sys-member-level.model';

describe('Component Tests', () => {
    describe('SysMemberLevel Management Component', () => {
        let comp: SysMemberLevelComponent;
        let fixture: ComponentFixture<SysMemberLevelComponent>;
        let service: SysMemberLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberLevelComponent],
                providers: []
            })
                .overrideTemplate(SysMemberLevelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMemberLevelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMemberLevelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysMemberLevel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysMemberLevels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
